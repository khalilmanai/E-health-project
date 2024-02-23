<?php

namespace App\Repository;

use App\Entity\Administateur;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Administateur>
 *
 * @method Administateur|null find($id, $lockMode = null, $lockVersion = null)
 * @method Administateur|null findOneBy(array $criteria, array $orderBy = null)
 * @method Administateur[]    findAll()
 * @method Administateur[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class AdministateurRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Administateur::class);
    }

//    /**
//     * @return Administateur[] Returns an array of Administateur objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('a')
//            ->andWhere('a.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('a.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Administateur
//    {
//        return $this->createQueryBuilder('a')
//            ->andWhere('a.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
